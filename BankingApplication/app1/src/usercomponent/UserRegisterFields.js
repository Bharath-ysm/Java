import { useState } from "react";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useNavigate } from "react-router-dom";
import Validation from "../navbarcomponent/Validation";


const AdminRegisterForm = () => {
  let navigate = useNavigate();

  const [registerRequest, setRegisterRequest] = useState({
    email:"",
    password:"",
    contact:""
  });
  const [errors,setErrors] = useState({});
  

 

  const handleUserInput = (e) => {
    setRegisterRequest({ ...registerRequest, [e.target.name]: e.target.value });
  };

  const handleValidation = (e) => {
    e.preventDefault();
    
  };


  const loginAction = (e) => {
    fetch("http://localhost:8080/api/user/admin/register", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(registerRequest),
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

    if(!registerRequest.email.trim())
    {
      ValidationErrors.email="email is required"
    }
    else if(!/\S+@\S+\.\S+/.test(registerRequest.email))
    {
      ValidationErrors.email="email is not valid"
    }
  
    if(!registerRequest.password.trim())
    {
      ValidationErrors.password="password is required"
    }
    else if(!/(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}/.test(registerRequest.password))
    {
      ValidationErrors.password="password is not valid"
    }

    if(!registerRequest.contact.trim())
    {
      ValidationErrors.contact="contact is required"
    }
    else if(!/[789][0-9]{9}/.test(registerRequest.contact))
    {
      ValidationErrors.contact="contact is not valid"
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
            <form onSubmit={handleValidation}>
              <div className="mb-3 text-color">
                <label for="emailId" class="form-label">
                  <b>Email Id</b>
                </label>
                <input
                  type="email"
                  className="form-control"
                  id="email"
                  name="email"
                  onChange={handleUserInput}
                  value={registerRequest.email}
                  
                />
                   {errors.email&& <span>{errors.email}</span>}
              </div>
              <div className="mb-3 text-color">
                <label for="password" className="form-label">
                  <b>Password</b>
                </label>
                <input
                  type="password"
                  className="form-control"
                  id="password"
                  name="password"
                  onChange={handleUserInput}
                  value={registerRequest.password}
                  
                  autoComplete="on"
                />
                   {errors.password && <span>{errors.password}</span>}
              </div>
              <div className="mb-3 text-color">
                <label for="contact" className="form-label">
                  <b>Contact</b>
                </label>
                <input
                  type="contact"
                  className="form-control"
                  id="contact"
                  name="contact"
                  onChange={handleUserInput}
                  value={registerRequest.contact}
                  autoComplete="on"
                />
              </div>
              <div className="mb-3 text-color">
                <label for="street" className="form-label">
                  <b>Street</b>
                </label>
                <input
                  type="street"
                  className="form-control"
                  id="street"
                  name="street"
                  onChange={handleUserInput}
                  value={registerRequest.street}
                  autoComplete="on"
                />
              </div>
              <div className="mb-3 text-color">
                <label for="city" className="form-label">
                  <b>City</b>
                </label>
                <input
                  type="city"
                  className="form-control"
                  id="city"
                  name="city"
                  onChange={handleUserInput}
                  value={registerRequest.city}
                  autoComplete="on"
                />
              </div>
              <div className="mb-3 text-color">
                <label for="pincode" className="form-label">
                  <b>Pincode</b>
                </label>
                <input
                  type="pincode"
                  className="form-control"
                  id="pincode"
                  name="pincode"
                  onChange={handleUserInput}
                  value={registerRequest.pincode}
                  autoComplete="on"
                />
              </div>
              <div className="mb-3 text-color">
                <label for="roles" className="form-label">
                  <b>Roles</b>
                </label>
                <input
                  type="roles"
                  className="form-control"
                  id="roles"
                  name="roles"
                  onChange={handleUserInput}
                  value={registerRequest.roles}
                  autoComplete="on"
                />
              </div>
              <div className="mb-3 text-color">
                <label for="age" className="form-label">
                  <b>Age</b>
                </label>
                <input
                  type="age"
                  className="form-control"
                  id="age"
                  name="age"
                  onChange={handleUserInput}
                  value={registerRequest.age}
                  autoComplete="on"
                />
              </div>
              <div className="mb-3 text-color">
                <label for="gender" className="form-label">
                  <b>Gender</b>
                </label>
                <select
                  className="form-select"
                  id="gender"
                  name="gender"
                  onChange={handleUserInput}
                  value={registerRequest.gender}
                >
                  <option value="select gender">select gender</option>
                  <option value="Male">Male</option>
                  <option value="Female">Female</option>
                  <option value="others">Others</option>
                </select>
              </div>
              <div className="mb-3 text-color">
                <label for="bankId" className="form-label">
                  <b>BankId</b>
                </label>
                <input
                  type="bankId"
                  className="form-control"
                  id="bankId"
                  name="bankId"
                  onChange={handleUserInput}
                  value={registerRequest.bankId}
                  autoComplete="on"
                />
              </div>
              
              <button
                type="submit"
                className="btn bg-color custom-bg-text"
                onClick={loginAction}
              >
                Login
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
