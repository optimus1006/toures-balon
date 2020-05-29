using Javeriana.Convenios.Api.Models;
using Javeriana.Convenios.Api.Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Threading.Tasks;

namespace Javeriana.Convenios.Api.Interfaces
{
    public interface IConvenioRepository : IRepositoryBase<Convenio>
    {
        IEnumerable<Convenio> GetAllConvenios();
        Convenio GetConvenioById(string identificacion);
        void CreateConvenio(Convenio convenio);
        Convenio UpdateConvenio(Convenio convenio);
        void DeleteConvenio(Convenio convenio);
    }
}
