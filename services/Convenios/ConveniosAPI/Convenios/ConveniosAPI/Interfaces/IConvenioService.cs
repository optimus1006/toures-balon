using Javeriana.Convenios.Api.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Javeriana.Convenios.Api.Interfaces
{
    public interface IConvenioService
    {
        public ConveniosGETAllRs ConveniosGETAll();
        public ConveniosGETByIdRs ConveniosGETById(string identificacion);
        public ConveniosPSTRs ConveniosPST(ConveniosPSTRq body);
        public ConveniosPCTRs ConveniosPCT(string identificacion, ConveniosPCTRq body);
        public void ConveniosDEL(string identificacion);
    }
}
