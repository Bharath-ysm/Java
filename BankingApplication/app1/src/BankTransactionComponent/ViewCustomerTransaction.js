import React, { useEffect, useState } from 'react'
import axios from 'axios'
const ViewCustomerTransaction=()=> 
{
    const[AllTransactions,setAllTransactions]=useState({})

    

    let jwtToken;

    let admin_jwtToken=sessionStorage.getItem("admin");

    let bank_jwtToken=sessionStorage.getItem("bank");

    let customer_jwtToken=sessionStorage.getItem("customer");



    if(admin_jwtToken)
    {
        jwtToken=admin_jwtToken;
    }
    else if(bank_jwtToken)
    {
        jwtToken=bank_jwtToken;
    }
    else
    {
        jwtToken=customer_jwtToken;
    }


    const retrieveAllTransaction= async()=>
    {
        const response=await axios.get
        (
            "http://localhost:8080/api/transaction/CustomerTransaction/byTimeRange?"+History,
            {
                header:{Authorization:"Bearer"+jwtToken}
            }
        )
        console.log(response.data)
        return response.data

    }

    useEffect(
        ()=>{

            const getAllTransactions=async()=>{
                const AllTransactions=await retrieveAllTransaction();
                if(AllTransactions)
                {
                    setAllTransactions(AllTransactions.bankTransactions);
                }
                getAllTransactions();   
            }
        },[]
    )

      const BankAccountTransaction=JSON.parse(sessionStorage.getItem("active bank Account"))
      const user=JSON.parse(sessionStorage.getItem("active user"));

      const formatDateFromEpoch=(epochTime)=>
      {
        
      }

  return 
  (
    <div>
    <div>
        <h1>ViewCustomerTransaction</h1>
    </div>
    <div>
        <table>
            <thead>
                <tr>
                    <th scope='col'>TransactionId</th>
                    <th scope='col'>SourceBank</th>
                    <th scope='col'>CustomerName</th>
                    <th scope='col'>SourceAccount</th>
                    <th scope='col'>TransactionType</th>
                    <th scope='col'>Amount</th>
                    <th scope='col'>Narration</th>
                    <th scope='col'>TransactionTIme</th>
                </tr>
            </thead>
            <tbody>
                  {  
                  AllTransactions.map((transaction)=>{
                        return
                        (
                    <tr>
                    <td>{transaction.BankAccountTransaction.TransactionId}</td>
                    <td>{transaction.Bank.SourceBank}</td>
                    <td>{transaction.Bank.CustomerName}</td>
                    <td>{transaction.BankAccountTransaction.SourceAccount}</td>
                    <td>{transaction.BankAccountTransactionTransactionType}</td>
                    <td>{transaction.BankAccountTransaction.Amount}</td>
                    <td>{transaction.BankAccountTransaction.Narration}</td>
                    <td>{transaction.BankAccountTransaction.TransactionTime}</td>
                    
                    

                </tr>
                        )

                    })}
                    
                </tbody>
        </table>
    </div>
    </div>
  )
}

export default ViewCustomerTransaction;