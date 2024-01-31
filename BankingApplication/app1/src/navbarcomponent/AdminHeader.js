import React from 'react'
import { useNavigate } from 'react-router-dom'
import { Link } from 'react-router-dom';

const AdminHeader=()=> {
    let navigate=useNavigate();
    const adminLogout=()=>{
        sessionStorage.removeItem("ActiveAdmin");
        sessionStorage.removeItem("AdminJwtToken");
        window.location.reload(true);
        setTimeout(()=>{
            navigate("/home");
        },2000)
    }

  return (
    <div>
         <ul>
            <li className='nav-item'> 
            <Link to="user/bank/register" 
            className="nav-link active" 
            aria-current="page">
                <b>
                    Register Bank Manager
                    </b>
                    </Link>
                </li>
            <li><Link to="user/admin/bank" className='nav-link active' aria-current="page"><b>Add Bank</b></Link></li>
            <li><Link to="user/admin/view/bank" className='nav-link active' aria-current="page"><b>View Bank</b></Link></li>
            <li><Link to="user/bank/bankmanagers" className='nav-link active' aria-current="page"><b>Bank Managers</b></Link></li>
            <li><Link to="user/bank/customers" className='nav-link active' aria-current="page"><b>All Customers</b> </Link></li>
            <li><Link to="user/bank/allbankaccounts" className='nav-link active' aria-current="page"><b>All Bank Account</b> </Link></li>
            <li><Link to="user/bank/allbanktransactions" className='nav-link active' aria-current="page"><b>All Transactions</b> </Link></li>
            <li><Link to="" className='nav-link active' aria-current="page" onClick={adminLogout} ><b className='text-color'>Logout</b> </Link></li>
         </ul>

    </div>
  )
}

export default AdminHeader