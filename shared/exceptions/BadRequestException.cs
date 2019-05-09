using System;

public class BadRequestException : Exception
{
    public object invalidObject { get; private set; }
    public BadRequestException()
    {
    }

    public BadRequestException(string message)
        : base(message)
    {
    }

    public BadRequestException(string message, Exception inner)
        : base(message, inner)
    {
    }
}