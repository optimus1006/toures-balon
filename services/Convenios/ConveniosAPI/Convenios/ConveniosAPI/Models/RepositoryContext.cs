using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Threading.Tasks;

namespace Javeriana.Convenios.Api.Models
{
    public class RepositoryContext : DbContext
    {
        public RepositoryContext(DbContextOptions options ) : base(options) {

        }

        public DbSet<Convenio> Convenio { get; set; }
        public DbSet<Pais> Pais { get; set; }
        public DbSet<Ciudad> Ciudad { get; set; }

    }
}
