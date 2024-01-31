export default function Validation(values)
{
    const errors ={}

    const EmailId_pattern = /^[^\s@]+@[^\s@]+\.[^\s@]{2,6}$/;
    const Password_pattern=/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{8,}$/;

    

    if(values.EmailId_pattern === "")
    {
        errors.EmailId = "Email is Required";
    }
    if(!EmailId_pattern.test(values.EmailId))
    {
        errors.EmailId = "Email is not correct";
    }
    
    if(values.Password === "")
    {
        errors.Password = "Password Required"; 
    }
    
    

    return errors;
}
