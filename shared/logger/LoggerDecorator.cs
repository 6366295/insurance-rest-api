using System.Collections.Generic;
using System.ComponentModel;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;

using Insurance.Repositories;

namespace Insurance.Logger
{
    class LoggerDecorator<T> : ILoggerDecorator<T>
    {
        private readonly IRepository<T> decoratedRepository;

        public LoggerDecorator(IRepository<T> decorate)
        {
            this.decoratedRepository = decorate;
        }

        public IEnumerable<T> getAll(ILogger log)
        {
            log.LogInformation("LoggerDecorator - getAll: Getting all objects");
            var result = this.decoratedRepository.getAll();

            log.LogInformation("LoggerDecorator - getAll: Got all objects");
            return result;
        }

        public T create(string requestBody, ILogger log)
        {
            try
            {
                log.LogInformation($"LoggerDecorator - create: Creating {requestBody}");
                T created = this.decoratedRepository.create(requestBody);

                log.LogInformation("LoggerDecorator - create: Success");
                return created;
            }
            catch (BadRequestException ex)
            {
                log.LogWarning($"LoggerDecorator - create: {ex}");
                throw ex;
            }
            catch (InvalidFieldException ex)
            {
                log.LogWarning($"LoggerDecorator - create: Invalid fields \n {ex} \n {ex.invalidObject}");
                throw ex;
            }
            catch (JsonSerializationException ex)
            {
                log.LogWarning($"LoggerDecorator - create: Invalid Json \n {ex}");
                throw ex;
            }
        }

        public T getById(string id, ILogger log)
        {
            try
            {
                log.LogInformation($"LoggerDecorator - getById: Getting object");
                T result = this.decoratedRepository.getById(id);

                log.LogInformation($"LoggerDecorator - getById: Got object");
                return result;
            }
            catch (BadRequestException ex)
            {
                log.LogWarning($"LoggerDecorator - getById: {ex.Message}");
                throw ex;
            }
        }

        public T updateById(string id, string requestBody, ILogger log)
        {
            try
            {
                log.LogInformation($"LoggerDecorator - updateById: Updating object");
                T result = this.decoratedRepository.updateById(id, requestBody);

                log.LogInformation($"LoggerDecorator - updateById: Updated object");
                return result;
            }
            catch (BadRequestException ex)
            {
                log.LogWarning($"LoggerDecorator - updateById: {ex}");
                throw ex;
            }
            catch (InvalidFieldException ex)
            {
                log.LogWarning($"LoggerDecorator - updateById: Invalid fields \n {ex} \n {ex.invalidObject}");
                throw ex;
            }
            catch (JsonSerializationException ex)
            {
                log.LogWarning($"LoggerDecorator - updateById: Invalid Json \n {ex}");
                throw ex;
            }
        }

        public T deleteById(string id, ILogger log)
        {
            try
            {
                log.LogInformation($"LoggerDecorator - deleteById: Deleting object");
                T result = this.decoratedRepository.deleteById(id);

                log.LogInformation($"LoggerDecorator - deleteById: Deleted object");
                return result;
            }
            catch (BadRequestException ex)
            {
                log.LogWarning($"LoggerDecorator - getById: {ex.Message}");
                throw ex;
            }
        }
    }
}