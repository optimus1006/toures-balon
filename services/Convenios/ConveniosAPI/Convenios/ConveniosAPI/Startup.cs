using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using HealthChecks.UI.Client;
using Javeriana.Convenios.Api.HealthChecks;
using Javeriana.Convenios.Api.Interfaces;
using Javeriana.Convenios.Api.Models;
using Javeriana.Convenios.Api.Repository;
using Javeriana.Convenios.Api.Services;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Diagnostics.HealthChecks;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.HttpsPolicy;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;

namespace ConveniosAPI
{
    public class Startup
    {
        public Startup(IConfiguration configuration) {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services) {
            services.AddControllers();
            services.AddDbContext<RepositoryContext>(options => options.UseNpgsql(Configuration.GetConnectionString("ConveniosConnection")));
            services.AddScoped<IRepositoryWrapper, RepositoryWrapper>();
            //services.AddSingleton<IConvenioService, ConvenioService>();
            services.AddSwaggerDocument( config => 
            {
                config.PostProcess = document => {
                    document.Info.Version = "1.0.0";
                    document.Info.Title = "Convenios API";
                    document.Info.Description = "Este es el API de Convenios para Toures Balon";
                    document.Info.TermsOfService = "Any TOS";
                    document.Info.Contact = new NSwag.OpenApiContact {
                        Name = "Archetype",
                        Email = "touresbalon@archetype.com"
                    };
                    document.Info.License = new NSwag.OpenApiLicense {
                        Name = "Apache 2.0",
                        Url = "http://www.apache.org/licenses/LICENSE-2.0.html"
                    };
                };
            });
            services.AddHealthChecks().AddCheck("memoria", new ApiHealthCheck());
            
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env) {
            if (env.IsDevelopment()) {
                app.UseDeveloperExceptionPage();
            }

            app.UseHttpsRedirection();

            app.UseRouting();

            app.UseAuthorization();

            app.UseEndpoints(endpoints => {
                endpoints.MapControllers();
                endpoints.MapHealthChecks("/health", new HealthCheckOptions {
                    Predicate = _ => true,
                    ResponseWriter = UIResponseWriter.WriteHealthCheckUIResponse
                });
            });
            app.UseOpenApi();
            app.UseSwaggerUi3();
        }
    }
}
