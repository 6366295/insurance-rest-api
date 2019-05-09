using System.Collections.Generic;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace Insurance.Repositories
{
    public interface IRepository<T>
    {
        IEnumerable<T> getAll();
        T create(string requestBody);
        T getById(string id);
        T updateById(string id, string requestBody);
        T deleteById(string id);
    }
}