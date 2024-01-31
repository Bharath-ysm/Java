import React, { useState } from 'react'
import { useLocation } from 'react-router-dom'
import axios from 'axios'

const ViewBankAccounStatement=()=>{

const location=useLocation();
const[customerId,setcustomerId]=([])

const[statementDownloadRequest,setStatementDownloadRequest]=useState({
  startDate:" ",
  EndDate:" ",
  accountId:" "
})

const handleUserInput=(e)=>
{
  setStatementDownloadRequest=({...statementDownloadRequest,[e.target.Name]:e.target.value})
}
let admin_jwtToken=jwtToken();

const RetrieveBankAccounts=async()=>
{
  const response=await axios.get
  (
    "http://localhost:8080/api/account/fetch/user/add"+customerId,
    {
      header: {Authorization:"Bearer"+admin_jwtToken}
    }
  )
}

const[bankAccount,setBankAccount]=useState({});

const convertToEpochTime=(dateString)=>
{
  const selectedDate=new Date(dateString);
  const epochTime=selectedDate.getTime();
  return epochTime();
}

let jwtToken = jwtToken();

const DownloadStatement=(e)=>
{
  e.preventDefault();
  fetch("http://localhost:8080/api/transaction/download/statement?accountId="+bankAccount.id+
"startDate="+convertToEpochTime(statementDownloadRequest.startDate)+"EndTime="+convertToEpochTime(statementDownloadRequest.EndDate),
   {method:"GET",
   headers:
   {
    Authorization:"Bearer"+jwtToken
   } }     ).then((response)=>response.blob()).then((blob)=>{const url=window.URL.createObjectURL(blob)})
}

const formatDateFromEpoch = (epochTime) => {
  const date = new Date(Number(epochTime));
  const formattedDate = date.toLocaleString(); // Adjust the format as needed

  return formattedDate;
};


function ViewBankAccounStatement() {
  return (
  
      <div>
      <form>
        <div>
        <label>startDate:</label>
        <input type="datetime-local" onChange={handleUserInput} value={statementDownloadRequest.startDate} required/>
        </div>

        <div>
          <label>EndDate:</label>
          <input type="datetime-local" onChange={handleUserInput} value={statementDownloadRequest.EndDate} required/>
        </div>
        <div>

          <label>AccountId:</label>
          <input type="number" onChange={handleUserInput} value={statementDownloadRequest.accountId} required/>
        </div>

        <div>
        <button type="DownloadStatement" onClick={DownloadStatement}/>
        </div>

        <div>
          <h1>customer Bank Details</h1>
        </div>

        <div>
        <label htmlFor="name" className="form-label">
                    <b>Bank Name</b>
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    value={customerId.bank.name}
                    readOnly
                  />
        </div>

        <div>
        <label htmlFor="name" className="form-label">
                    <b>IfscCode</b>
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    value={bankAccount.number}
                    readOnly
                  />
        </div>

        <div>
        <label htmlFor="name" className="form-label">
                    <b>CustomerName</b>
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    value={customerId.bank.name}
                    readOnly
                  />
        </div>

        <div>
        <label htmlFor="name" className="form-label">
                    <b>customerContact</b>
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    value={customerId.contact}
                    readOnly
                  />
        </div>

        <div>
        <label htmlFor="name" className="form-label">
                    <b>creation Date</b>
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    value={formatDateFromEpoch((bankAccount.creationDate))}
                    readOnly
                  />
        </div>

        <div>
        <label htmlFor="name" className="form-label">
                    <b>AvailableBalance</b>
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    value={bankAccount.balance}
                    readOnly
                  />
        </div>

        <div>
        <label htmlFor="name" className="form-label">
                    <b>Accountstatus</b>
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    value={bankAccount.Status}
                    readOnly
                  />
        </div>

        
    
      </form>
    </div>
  )
}
}
export default ViewBankAccounStatement;