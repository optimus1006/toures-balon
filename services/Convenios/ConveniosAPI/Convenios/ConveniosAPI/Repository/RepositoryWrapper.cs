using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Javeriana.Convenios.Api.Interfaces;
using Javeriana.Convenios.Api.Models;

namespace Javeriana.Convenios.Api.Repository
{
    public class RepositoryWrapper : IRepositoryWrapper {

        private RepositoryContext _context;
        private IConvenioRepository _convenio;
        private IPaisRepository _pais;
        private ICiudadRepository _ciudad;

        public IConvenioRepository Convenio
        {
            get {
                if(_convenio == null) {
                    _convenio = new ConvenioRepository(_context);
                }
                return _convenio;
            }
        }

        public IPaisRepository Pais 
        {
            get {
                if (_pais == null) {
                    _pais = new PaisRepository(_context);
                }
                return _pais;
            }
        }

        public ICiudadRepository Ciudad 
        {
            get {
                if (_ciudad == null) {
                    _ciudad = new CiudadRepository(_context);
                }
                return _ciudad;
            }
        }
        public RepositoryWrapper(RepositoryContext repositoryContext) {
            _context = repositoryContext;
        }

        public void Save() {
            throw new NotImplementedException();
        }
    }
}
