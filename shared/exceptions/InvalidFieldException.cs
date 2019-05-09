using System;

public class InvalidFieldException : Exception
{
    public object invalidObject { get; private set; }
    public InvalidFieldException()
    {
    }
    public InvalidFieldException(object obj)
    {
        this.invalidObject = obj;
    }

    public InvalidFieldException(string message)
        : base(message)
    {
    }

    public InvalidFieldException(string message, Exception inner)
        : base(message, inner)
    {
    }
}