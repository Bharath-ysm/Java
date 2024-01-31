import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import axios from 'axios'

 const ViewAllBankCustomers=() =>{
    let navigate=useNavigate();
   const [allCustomer,setallcustomer]=useState([]);
   const[CustomerName,setcustomername]=useState("");
   const[tempCustomerName,settempcustomername]=("");
   const[UpdateUserstatusRequest,setupdateuserstatusrequest]=useState({usetId:"Status:"});
   const admin_JwtToken=sessionStorage.getItem("admin_JwtToken");
   const retrieveBankAllCustomerByName=async()=> {
   const response=await axios.get("http://localhost:8080/api/user/all/customer/serach"+"customerName="+CustomerName,
   {
    header:{Authorization:"Bearer"+admin_JwtToken},
   })
    console.log(response.data)
    return response.data 
   }

   useEffect(()=>{
    if(CustomerName!==""){
      const getAllCustomerByName=async()=>{
        const customer=await retrieveBankAllCustomerByName();
        if(customer){
          setallcustomer(customer.users);
        }
      }
      getAllCustomerByName();
    }
    else{
     const getAllCustomer=async()=>{
      const customer=await retrieveAllCustomer();
      if(customer){
        setallcustomer(customer.users);
      }
     }
     getAllCustomer();
    }
   },[CustomerName]);

   const retrieveAllCustomer=async()=> {
   const response=await axios.get("http://localhost:8080/api/user/all/fetch/All Bank Users?role=customer",
   {
    header:{Authorization:"Bearer"+admin_JwtToken}
   })
    console.log(response.data)
    return response.data 
   
   }
   

   const searchByCustomerName=(e)=>{
    e.preventDefault();
    setcustomername(tempCustomerName);
  }
  const viewAccountDetails=(customer)=>{
    customer.preventDefault();
    navigate("customer/bank/Account/details",{state:customer});
  }
    return (
      <div>
      <div>
        <h2>All Bank Customers</h2>
      </div>
      <form>
    <div>
      <b>CustomerName</b>
      <label>
      <input type='text' placeholder='Enter customer name' onChange={(e)=>settempcustomername(e.target.value)}
      value={tempCustomerName} required/>
      </label>
    </div>
    <div>
      <button type='submit' onClick={searchByCustomerName}>
        serach
      </button>
    </div>
    <div>
    <table>
      <thead>
        <tr>
          <th scope='col'>BankName</th>
          <th scope='col'>Email</th>
          <th scope='col'>Gender</th>
          <th scope='col'>Contact</th>
          <th scope='col'>Street</th>
          <th scope='col'>City</th>
          <th scope='col'>Pincode</th>
          <th scope='col'>AccountDetails</th>
          <th scope='col'>Status</th>
        </tr>
      </thead>
      <tbody>
        {allCustomer.map((customer)=>{
          return(
            <tr>
              <td>
                <b>{customer.user.Bankname}</b>
              </td>
              <td>
                <b>{customer.user.email}</b>
              </td>
              <td>
                <b>{customer.user.gender}</b>
              </td>
              <td>
                <b>{customer.user.contact}</b>
              </td>
              <td>
                <b>{customer.user.street}</b>
              </td>
              <td>
                <b>{customer.user.city}</b>
              </td>
              <td>
                <b>{customer.user.pincode}</b>
              </td>
              <td>{(()=>{if(customer.isAccountLinked==="Yes")
              {
                return(<button onClick={()=>viewAccountDetails(customer)}>viewAccount</button>);
              }})()}

              {(()=>{if(customer.isAccountLinked!=="Yes")
              {
                return(<button onClick={()=>viewAccountDetails(customer)}>viewAccount</button>);
              }})()}
                </td>
                <td>
                <b>{customer.user.status}</b>
                </td>
            </tr>
          )
        }
        )}
      </tbody>
    </table>
    </div>
    </form>
    </div>
  )
}
export default ViewAllBankCustomers;