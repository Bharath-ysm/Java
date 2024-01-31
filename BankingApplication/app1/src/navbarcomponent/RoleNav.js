import React from 'react'
import AdminHeader from './AdminHeader'
import BankHeader from './BankHeader'
import CustomerHeader from './CustomerHeader'
import NormalHeader from './NormalHeader'


const RoleNav=()=> 
{

    const customer=JSON.parse(sessionStorage.getItem("customer"))

    const admin=JSON.parse(sessionStorage.getItem("admin"))

    const bank=JSON.parse(sessionStorage.getItem("bank"))

    if(admin != null)
    {
        return<AdminHeader/>
    }
    else if(bank != null)
    {
        return<BankHeader/>
    }
    else if(customer != null)
    {
        return<CustomerHeader/>
    }
    else
    {
        return<NormalHeader/>
    }

  return (
    <div>RoleNav</div>
  )
}

export default RoleNav;