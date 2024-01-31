import React from 'react'
import { useNavigate } from 'react-router-dom'
import { useState } from 'react';
import { useEffect } from 'react';
import axios from 'axios';

const AddBankForm=()=>{

    let navigate=useNavigate();

    const[Banks,setAllBanks]=useState({
        name:'',
        code:' ',
        Address:' ',
        Email:' ',
        Website:' ',
        Country:' ',
        Currency:' ',
        userId:' '
      })

    const[Users,setBankUsers]=useState({})

     const admin_jwtToken = sessionStorage.getItem("admin_jwtToken");
     const bank_jwtToken = sessionStorage.getItem("bank_jwtToken");
     

     const retrieveAllBankManagers= async()=>{
    try
    {
      const response=await axios.get
      (
        "http://localhost:8080/api/user/fetch/AllBankManagers",
        {
         headers:{Authorization:"Bearer"+admin_jwtToken},
        }
      );
      return response.data; // return the data from the response
    }
    catch (error) {
        console.error("error fetching bankmanagers",error);
        throw error;
    }
  };

  useEffect(()=>
    {
      const getAllBankUsers= async()=>{
        const AllBankUsers= await retrieveAllBankManagers();
        if(AllBankUsers)
        {
          setBankUsers(AllBankUsers.Users);
        }
      }
      getAllBankUsers();
    },[retrieveAllBankManagers]); //Include retriveAllBankManagers as a dependency
    
    const handleInput = (e) => {
        setBankUsers({...Banks, [e.target.name]: e.target.value })
    }; 
    const SaveBank = (e) => {
        fetch("http://localhost:8080/api/bank/add", 
        {
          method: "POST",
          headers: 
          {
            Accept: "application/json",
            "Content-Type": "application/json",
            Authorization: "Bearer " + bank_jwtToken,
          },
          body: JSON.stringify(Banks),
        })
          .then((result) => {
            console.log("result", result);
            result.json().then((res) => {
              console.log(res);
    
              if (res.success) {
                console.log("Got the success response");
            }})})
          
}
  return (
    <div>
    <div>AddBankForm</div>
    <div>
        <form onSubmit={SaveBank}>
            <div>
                <label><b>BankName</b></label>
                <input type='text' 
                onChange={handleInput} 
                value={Banks.name}/>
            </div>
            <div>
                <label><b>BankCode</b></label>
                <input type='text' 
                onChange={handleInput} 
                value={Banks.code}/>
            </div>
            <div>
                <label><b>Address</b></label>
                <input type='text' 
                onChange={handleInput} 
                value={Banks.Address}/>
            </div>
            <div>
                <label><b>Email</b></label>
                <input type='text' 
                onChange={handleInput} 
                value={Banks.Email}/>
            </div>
            <div>
                <label><b>WebSite</b></label>
                <input type='text' 
                onChange={handleInput} 
                value={Banks.Website}/>
            </div>
            <div>
                <label><b>Country</b></label>
                <input type='text' 
                onChange={handleInput} 
                value={Banks.Country}/>
            </div>
            <div>
                <label><b>Currency</b></label>
                <input type='text' 
                onChange={handleInput} 
                value={Banks.Currency}/>
            </div>
            <div>
                <label><b>userId</b></label>
                <input type='text' 
                onChange={handleInput} 
                value={Banks.userId}/>
            </div>
            <div>
                <button 
                type="submit"
                onClick={SaveBank}
                >
                    Register Bank
                </button>
            </div>
        </form>
    </div>
    </div>
  )
}
export default AddBankForm;
