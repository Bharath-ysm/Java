import React, { useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom'
import {toast} from 'react-toastify'

const AddBankAccount=()=>{
    const location=useLocation();
    const customer=location.state();
    const bank=JSON.parse(sessionStorage.getItem("Active-bank"));
    const[BankAccount,setBankAccount]=useState({

        name:" ",
        IfscCode:" ",
        type:" ",
        bankId:bank.bank.id,
        userId:customer.id

    })

    let navigate=useNavigate();

    const handleInput=(e)=>
    {
        setBankAccount=({...BankAccount,[e.targetName]:e.targetValue})
    }
    let jwtToken=jwtToken();
    let bank_jwtToken=jwtToken();

    const saveAccount = (e) => {
        fetch("http://localhost:8080/api/bank/account/add", {
          method: "POST",
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
            Authorization: "Bearer " + bank_jwtToken,
          },
          body: JSON.stringify(BankAccount),
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
                  navigate("/customer/bank/account/detail", { state: customer });
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
                setTimeout(() => {
                  window.location.reload(true);
                }, 1000); // Redirect after 3 seconds
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
            setTimeout(() => {
              window.location.reload(true);
            }, 1000); // Redirect after 3 seconds
          });
        e.preventDefault();
      };
}

export default AddBankAccount;