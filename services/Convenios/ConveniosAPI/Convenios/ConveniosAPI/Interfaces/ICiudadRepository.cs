﻿using Javeriana.Convenios.Api.Models;
using Javeriana.Convenios.Api.Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Threading.Tasks;

namespace Javeriana.Convenios.Api.Interfaces
{
    public interface ICiudadRepository : IRepositoryBase<Ciudad>
    {
        public IEnumerable<Ciudad> FindByCondition(int codigo);
    }
}
