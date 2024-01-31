import React, { useState } from 'react'

    const CustomerAccountFundTransfer=()=>{
  
    const [TransferRequest,setTransferRequest]=useState({
      userId:customer.id,
      bankId:customer.bank.id,
      amount:"",
      toBankAccount:"",
      toBankIfsc:"",
      purpose:""
    })
    const customer=JSON.parse(sessionStorage.getItem("active Customer"))
    const handleInput=(e)=>{
      setTransferRequest({...bank,[e.target.acount]:e.target.value})
    };
    const SaveFunds=(e)=>{
      fetch("http://localhost:8080/api/transaction/accountTransfer",
      {
        method:"post",
        headers:{Accept:"application/json","content Type":"application/json",Authorization:"Bearer"+ bank_jwtToken},
        body:JSON.stringify(TransferRequest),
      })
      .then((result) => {
        console.log("result", result);
        result.json().then((res) => {
          console.log(res);

          if (res.success) {
            console.log("Got the success response");
            setTimeout(() => {
                navigate("/customer/bank/account/statement", { state: customer });
              }, 1000); // Redirect after 3 seconds
            } else {
                console.log("Didn't got success response");
                setTimeout(() => {
                    window.location.reload(true);
                  }, 1000); // Redirect after 3 seconds
        }})}).catch((error) => {
            console.error(error);
            setTimeout(() => {
                window.location.reload(true);
              }, 1000); // Redirect after 3 seconds
            });
          e.preventDefault();
        };

  return (
    <div>
    <div>
      <h5>CustomerFundTransfer</h5>
    </div>
    <div>
      <form>
        <div>
        <label >
          <b>AccountNumber</b>
        </label>
        <input type="number"
        onChange={handleInput}
        value={bank.accountnumber}></input>
        </div>
        <div>
          <label>
            <b>Ifsc Code</b>
          </label>
          <input 
          type="number"
          onChange={handleInput}
          value={bank.ifscCode}>
          </input>
        </div>
        <div>
          <label>
            <b>Amount</b>
          </label>
          <input 
          type="number"
          onChange={handleInput}
          value={bank.amount}>
          </input>
        </div>
        <div>
          <label>
           <b>purpose</b>
          </label>
          <input 
          type="text"
          onChange={handleInput}
          value={bank.purpose}>
          </input>
        </div>
        <div>
          <button type="submit"
          onClick={SaveFunds}>
            Transfer
          </button>
        </div>
      </form>
    </div>
    </div>
  )
};
export default CustomerAccountFundTransfer;