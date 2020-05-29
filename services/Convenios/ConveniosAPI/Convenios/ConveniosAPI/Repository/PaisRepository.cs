using Javeriana.Convenios.Api.Interfaces;
using Javeriana.Convenios.Api.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Threading.Tasks;

namespace Javeriana.Convenios.Api.Repository
{
    public class PaisRepository : RepositoryBase<Pais>, IPaisRepository
    {
        public PaisRepository(RepositoryContext repositoryContext) : base(repositoryContext) {

        }
        public IEnumerable<Pais> FindByCondition(int codigo) {
            return FindByCondition(a => a.Codigo.Equals(codigo));
        }
    }
}
