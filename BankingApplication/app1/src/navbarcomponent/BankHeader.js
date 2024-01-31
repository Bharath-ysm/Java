import React from 'react'
import { Link, useNavigate } from 'react-router-dom'

const BankHeader=()=> 
{

    let navigate=useNavigate();
    const BankLogout=()=>
    {
        sessionStorage.removeItem("active-bank");
        sessionStorage.removeItem("bank-jwtToken");
        window.location.reload(true);
        setTimeout(()=>{
            navigate("/home");
        },2000)
    }


  return (
    <div>
    <div>
        <h3>BankHeader</h3>
    </div>
    <div>
        <ul>
            <li className='nav-item'>
                <Link to=" " className='nav-link-active' aria-current="page">
                    <b className='text-color'>
                        Register Customer
                    </b>
                </Link>
            </li>
            <li className='nav-item'>
                <Link to=" " className='nav-link-active' aria-current="page">
                    <b className='text-color'>
                        Bank Accoounts
                    </b>
                </Link>
            </li>
            <li className='nav-item'>
                <Link to=" " className='nav-link-active' aria-current="page">
                    <b className='text-color'>
                        Bank Customers
                    </b>
                </Link>
            </li>
            <li className='nav-item'>
                <Link to=" " className='nav-link-active' aria-current="page">
                    <b className='text-color'>
                        Customer Transactions
                    </b>
                </Link>
            </li>
            <li className='nav-item'>
                <Link to=" " className='nav-link-active' aria-current="page" onClick={BankLogout}>
                    <b className='text-color'>
                        Logout
                    </b>
                </Link>
            </li>
        </ul>
    </div>
    </div>
  )
}

export default BankHeader