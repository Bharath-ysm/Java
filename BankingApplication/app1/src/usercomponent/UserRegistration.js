import { useState } from "react";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useNavigate } from "react-router-dom";

const AdminRegisterForm = () => {
  let navigate = useNavigate();

  //const [registerRequest, setRegisterRequest] = useState({});

  const[formData,setFormData]=useState(
    {
      email:"",
      password:""
    }
  )

  const[errors,setErrors]=useState({});
  

 const handleUserInput=(e)=>
 {
  const{name,value}=e.target;
  setFormData(
    {
      ...formData,[name]:value
    }
  )
 }

 const handleSubmit=(e)=>
 {
  e.preventDefault()
 }

  

  const loginAction = (e) => {
    fetch("http://localhost:8080/api/user/admin/register", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    })
      .then((result) => {
        console.log("result", result);
        result.json().then((res) => {
          console.log(res);

          if (res.success) {
            console.log("Got the success response");

            toast.success(res.responseMessage, {
              position: "top-center",
              autoClose: 1000,
              hideProgressBar: false,
              closeOnClick: true,
              pauseOnHover: true,
              draggable: true,
              progress: undefined,
            });
            setTimeout(() => {
              window.location.href = "/user/login";
            }, 1000); // Redirect after 3 seconds
          } else {
            console.log("Didn't got success response");
            toast.error("It seems server is down", {
              position: "top-center",
              autoClose: 1000,
              hideProgressBar: false,
              closeOnClick: true,
              pauseOnHover: true,
              draggable: true,
              progress: undefined,
            });
          }
        });
      })
      .catch((error) => {
        console.error(error);
        toast.error("It seems server is down", {
          position: "top-center",
          autoClose: 1000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
        });
      });
    e.preventDefault();
    const ValidationErrors={}

    if(!formData.email.trim())
    {
      ValidationErrors.email="email is required"
    }
    else if(!/\S+@\S+\.\S+/.test(formData.email))
    {
      ValidationErrors.email="email is not valid"
    }
  
    if(!formData.password.trim())
    {
      ValidationErrors.password="password is required"
    }
    else if(!/(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}/.test(formData.password))
    {
      ValidationErrors.password="password is not valid"
    }
    setErrors(ValidationErrors)
  
      if(Object.keys(ValidationErrors).length === 0)
      {
        alert("form submitted successfully")
      }
  
  };



  return (
    <div>
      <div className="mt-2 d-flex aligns-items-center justify-content-center">
        <div
          className="card form-card border-color custom-bg"
          style={{ width: "25rem" }}
        >
          <div className="card-header bg-color text-center custom-bg-text">
            <h4 className="card-title">Admin Register</h4>
          </div>
          <div className="card-body">
            <form onSubmit={handleSubmit}>
              <div className="mb-3 text-color">
                <label for="emailId" class="form-label" style={{color:"red"}}>
                  <b>Email Id</b>
                </label>
                <input
                  type="email"
                  className="form-control"
                  id="email"
                  name="email"
                  onChange={handleUserInput}
                  
                />
                {errors.email && <span>{errors.email}</span>}
              </div>
              <div className="mb-3 text-color">
                <label for="password" className="form-label" style={{color:"red"}}>
                  <b>Password</b>
                </label>
                <input
                  type="password"
                  className="form-control"
                  id="password"
                  name="password"
                  onChange={handleUserInput}
                  autoComplete="off"
                />
                {errors.password && <span>{errors.password}</span>}
              </div>
              <button
                type="submit" style={{backgroundColor:"#46d2d2"}}
                className="btn bg-color custom-bg-text"
                onClick={loginAction}
              >
                Register
              </button>
              <ToastContainer />
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AdminRegisterForm;
