import React from 'react'
import { Link, useNavigate } from 'react-router-dom'

const CustomerHeader=()=>
{

    let navigate=useNavigate();


    const customer=JSON.parse(sessionStorage.getItem("active-customer"));

    const handleTransactionHistory=()=>
    {

        navigate("/customer/bank/account/statement",{state:customer})
    }

    const ViewBankAccount=()=>{
        if(customer.isAccountLinked === "yes")
        {
            navigate("/customer/bank/account/detail",{state:customer})
        }
        else
        {
            console.error("bankAccount is not Linked,contact bank administrator");
        }
    }
    const BankTransactionHistory=()=>
    {
        if(customer.isAccountLinked === "yes")
        {
            navigate("/customer/account/transaction",{state:customer})
        }
        else
        {
            console.error("/bank account is not linked contact bank admin")
        }
    }
    const MoneyTransfer=()=>
    {
        if(customer.isAccountLinked === "yes")
        {
            navigate("/customer/account/transfer",{state:customer})
        }
        else
        {
            console.error("Account is NotLinked")
        }
    }
    const CustomerLogout=()=>
    {
        sessionStorage.removeItem("active-customer");
        sessionStorage.removeItem("customer-jwtToken");
        window.location.reload(true);
        setTimeout(()=>
        {
            navigate("/home");
        },2000)
    }




  return (
    <div>
    <div>
        <h3>CustomerHeader</h3>
    </div>
    <div>
        <ui>
        <li className='nav-item'>
                <Link to=" " className='nav-link-active' aria-current="page" onClick={MoneyTransfer}>
                    <b className='text-color'>
                        Money Transfer
                    </b>
                </Link>
        </li>
        <li className='nav-item'>
            <Link to=" " className='nav-link-active' aria-current="page" onClick={ViewBankAccount}>
                <b className='text-color'>
                    BankAccount
                </b>
            </Link>
        </li>
        <li className='nav-item'>
            <Link to=" " className='nav-link-active' aria-current="page" onClick={BankTransactionHistory}>
                <b className='text-color'>
                    Transaction History
                </b>
            </Link>
        </li>
        <li className='nav-item'>
            <Link to=" " className='nav-link-active' aria-current="page" onClick={CustomerLogout}>
                <b className='text-color'>
                    Logout
                </b>
            </Link>
        </li>
        </ui>
    </div>
    </div>
  )
}

export default CustomerHeader