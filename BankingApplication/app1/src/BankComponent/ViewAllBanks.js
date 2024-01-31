import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'

const ViewAllBanks=()=> 
{

  let navigate=useNavigate(); 
  const retreiveAllBanks= async()=>
    {
        const response=await axios.get
        (
            "http://localhost:8080/api/account/fetch/all?"+"AllBanks",
            {
                header:{Authorization:"Bearer"+admin_jwtToken},
            }
        )
          console.log(response)
          return response
    }

  

    useEffect(()=>
    {

        const getAllBanks = async()=>{
          const AllBanks=await retreiveAllBanks();
          if(AllBanks)
          {
            setAllBanks(AllBanks.Banks);
          }
        }
        getAllBanks();

    }
    )

 

  return 
  (
    <div>
      <h2>AllBanks</h2>
      <div>
        <table>
          <thead>
            <tr>
              <th scope='col'>BankName</th>
              <th scope='col'>BankCode</th>
              <th scope='col'>Address</th>
              <th scope='col'>PhoneNumber</th>
              <th scope='col'>Email</th>
              <th scope='col'>Website</th>
              <th scope='col'>Country</th>
              <th scope='col'>Currency</th>
            </tr>
          </thead>

        <tbody>
          {
            AllBanks.map((Banks)=>{
              return
              (
                <tr>
                  <td><b>{Banks.BankName}</b></td>
                  <td><b>{Banks.BankCode}</b></td>
                  <td><b>{Banks.Address}</b></td>
                  <td><b>{Banks.PhoneNumber}</b></td>
                  <td><b>{Banks.Email}</b></td>
                  <td><b>{Banks.Website}</b></td>
                  <td><b>{Banks.Country}</b></td>
                  <td><b>{Banks.Currency}</b></td>
                </tr>
              )
            })
          }
        </tbody>

        </table>
      </div>
    </div>
  )

}

export default ViewAllBanks;