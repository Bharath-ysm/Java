import React, { useEffect } from 'react'

function RetrieveAllBankAccounts() {

    useEffect(()=>{
      const getAllBanks=async()=>{
        const manager=await retrieveAllManagers();

        if(manager)
        {
          getAllBanks(manager.users)
        }
      }
      getAllBanks();
    })

  return (
    <div>RetrieveAllBankAccounts</div>
  )
}

export default RetrieveAllBankAccounts