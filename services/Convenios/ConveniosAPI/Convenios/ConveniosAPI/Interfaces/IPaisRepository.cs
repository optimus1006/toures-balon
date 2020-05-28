using Javeriana.Convenios.Api.Models;
using Javeriana.Convenios.Api.Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Threading.Tasks;

namespace Javeriana.Convenios.Api.Interfaces
{
    public interface IPaisRepository : IRepositoryBase<Pais>
    {
        public IEnumerable<Pais> FindByCondition(int codigo);
    }
}
