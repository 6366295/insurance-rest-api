using Microsoft.Extensions.Logging;
using System.Collections.Generic;

namespace Insurance.Logger
{
    public interface ILoggerDecorator<T>
    {
        IEnumerable<T> getAll(ILogger log);
        T create(string requestBody, ILogger log);
        T getById(string id, ILogger log);
        T updateById(string id, string requestBody, ILogger log);
        T deleteById(string id, ILogger log);
    }
}