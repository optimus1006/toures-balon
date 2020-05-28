using Javeriana.Convenios.Api.Interfaces;
using Javeriana.Convenios.Api.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Javeriana.Convenios.Api.Repository
{
    public class ConvenioRepository : RepositoryBase<Convenio>, IConvenioRepository
    {
        public ConvenioRepository (RepositoryContext repositoryContext) : base (repositoryContext) {
            
        }
        public IEnumerable<Convenio> GetAllConvenios() {
            return FindAll()
                .OrderBy(c => c.Identificacion)
                .ToList();
        }

        public Convenio GetConvenioById(string identificacion) {
            return FindByCondition(convenio => convenio.Identificacion.Equals(identificacion))
                .FirstOrDefault();
        }

        public void CreateConvenio(Convenio convenio) {
            Create(convenio);
            Save();
        }

        public Convenio UpdateConvenio(Convenio convenio) {
            Update(convenio);
            Save();
            return convenio;
        }

        public void DeleteConvenio(Convenio convenio) {
            Delete(convenio);
            Save();
        }
    }
}
