using Javeriana.Convenios.Api.Exceptions;
using Javeriana.Convenios.Api.Interfaces;
using Javeriana.Convenios.Api.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Javeriana.Convenios.Api.Services
{
    public class ConvenioService : IConvenioService
    {

        readonly RepositoryContext convenioContext;

        private List<Convenio> convenios;

        public ConvenioService(RepositoryContext context) {
            //this.convenios = new List<Convenio>();
            convenioContext = context;
        }

        public ConveniosGETAllRs ConveniosGETAll() {

            var listConvenios = convenioContext.Convenio;

            if (listConvenios == null)
                throw new ConvenioNoExisteException("No existe el convenio");
            return new ConveniosGETAllRs { Convenios = listConvenios.ToList() };
        }

        public ConveniosGETByIdRs ConveniosGETById(string identificacion) {

            var convenioPorId = convenioContext.Convenio.Find(identificacion) as Convenio;

            //var c = convenios.Find(c => c.Identificacion.Equals(identificacion));
            if (convenioPorId == null) throw new ConvenioNoExisteException("No existe el convenio");
            ConveniosGETByIdRs rs = new ConveniosGETByIdRs {
                Convenio = convenioPorId
            };
            return rs;
        }

        public ConveniosPCTRs ConveniosPCT(string identificacion, ConveniosPCTRq body) {
            var convenio = convenioContext.Convenio.Find(identificacion);
            if (convenio != null) {
                
                convenioContext.Convenio.Update(body.Convenio);
                //convenioContext.Entry(body.Convenio).State = EntityState.Modified;
                convenioContext.SaveChanges();

                ConveniosPCTRs rs = new ConveniosPCTRs {
                    Convenio = new Convenio()
                };

                if (body.Convenio.TipoConvenio != null)
                    rs.Convenio.TipoConvenio = body.Convenio.TipoConvenio;
                if (body.Convenio.Ciudad != null)
                    rs.Convenio.Ciudad = body.Convenio.Ciudad;
                if (body.Convenio.Correo != null)
                    rs.Convenio.Correo = body.Convenio.Correo;
                if (body.Convenio.TipoConvenio != null)
                    rs.Convenio.FechaVigencia = body.Convenio.FechaVigencia;
                if (body.Convenio.Identificacion != null)
                    rs.Convenio.Identificacion = body.Convenio.Identificacion;
                if (body.Convenio.NombreProveedor != null)
                    rs.Convenio.NombreProveedor = body.Convenio.NombreProveedor;

                //convenios[convenios.FindIndex(c => c.Identificacion == body.Convenio.Identificacion)] = rs.Convenio;

                return rs;
            } else {
                throw new ConvenioNoExisteException("No existe el convenio");
            }
        }

        public ConveniosPSTRs ConveniosPST(ConveniosPSTRq body) {
            //var convenio = convenios.Find(c => c.Identificacion == body.Convenio.Identificacion);
            var convenio = convenioContext.Convenio.Find(body.Convenio.Identificacion);

            if (convenio == null) {
                convenioContext.Convenio.Attach(body.Convenio);
                convenioContext.Entry(body.Convenio).State = EntityState.Modified;

                convenioContext.Convenio.Add(body.Convenio);
                convenioContext.SaveChanges();
            }
            else
                throw new ConvenioYaExisteException("El convenio ya existe");

            if (convenio == null) {
                //convenios.Add(body.Convenio);

                ConveniosPSTRs rs = new ConveniosPSTRs {
                    Convenio = new Convenio {
                        TipoConvenio = body.Convenio.TipoConvenio,
                        Ciudad = body.Convenio.Ciudad,
                        Correo = body.Convenio.Correo,
                        FechaVigencia = body.Convenio.FechaVigencia,
                        Identificacion = body.Convenio.Identificacion,
                        NombreProveedor = body.Convenio.NombreProveedor
                    }
                };
                return rs;
            } 
            else {
                throw new ConvenioNoExisteException("Ya existe el convenio");
            }
        }

        public void ConveniosDEL(string identificacion) {
            var convenio = convenios.FindAll(c => c.Identificacion.Equals(identificacion));
            if (convenio == null)
                throw new ConvenioNoExisteException("No existe el convenio");
            foreach (var item in convenio) {
                convenios.Remove(item);
            }
        }
    }
}
