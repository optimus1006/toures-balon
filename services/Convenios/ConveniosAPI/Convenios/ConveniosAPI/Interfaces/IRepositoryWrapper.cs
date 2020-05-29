using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Javeriana.Convenios.Api.Interfaces
{
    public interface IRepositoryWrapper
    {
        IConvenioRepository Convenio { get; }
        IPaisRepository Pais { get; }
        ICiudadRepository Ciudad { get; }
        void Save();
    }
}
