using Javeriana.Convenios.Api.Interfaces;
using Javeriana.Convenios.Api.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Javeriana.Convenios.Api.Repository
{
    public class CiudadRepository : RepositoryBase<Ciudad>, ICiudadRepository
    {
        public CiudadRepository(RepositoryContext repositoryContext) : base(repositoryContext) {

        }
        public IEnumerable<Ciudad> FindByCondition(int codigo) {
            return FindByCondition(a => a.PaisCodigo.Equals(codigo));
        }
    }
}
