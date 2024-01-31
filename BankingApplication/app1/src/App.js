import logo from './logo.svg';
import './App.css';


import {Login} from './usercomponent/Login';
import UserRegistration from './usercomponent/UserRegistration';
import { Route, Routes } from 'react-router-dom';
import NormalHeader from './navbarcomponent/NormalHeader';
import UserRegisterFIelds from './usercomponent/UserRegisterFields';
import HomePage from './PageComponent/HomePage';
import AddBankForm from './BankComponent/AddBankForm';
import ViewAllBanks from './BankComponent/ViewAllBanks';
import AdminRegisterForm from './usercomponent/UserRegistration';
import ViewAllBankManagers from './usercomponent/ViewAllBankManagers';
import AddBankAccount from './BankAccountComponent/AddBankAccount';
import RetrieveAllBankAccounts from './usercomponent/RetrieveAllBankAccounts';
import CustomerAccountFundTransfer from './BankTransactionComponent/CustomerAccountFundTransfer';
import About from './PageComponent/About';
import Contact from './PageComponent/Contact';
import ViewBankAccounStatement from './BankAccountComponent/ViewBankAccounStatement';
import ViewCustomerTransaction from './BankTransactionComponent/ViewCustomerTransaction';
import ViewAllBankCustomers from './usercomponent/ViewAllBankCustomers';
import OnlineBanking from './PageComponent/OnlineBanking';
import Header from './navbarcomponent/Header';


function App() 
{
  return (
    
     <>
        <Header/>
       {/* <NormalHeader/> */}
      <Routes>
        <Route path='/about' element={<About />}/>
        <Route path='/contact' element={<Contact/>}/>
        <Route path='/login' element={<Login/>}/>
        <Route path='/registration' element={<UserRegistration/>}/>
        <Route path='/user' element={<UserRegisterFIelds/>}/>
        <Route path='/' element={<HomePage/>}/>
        <Route path='user/customer/register' element={<UserRegistration/>}/>
        <Route path='/admin/bank/register' element={<AddBankForm/>}/>
        <Route path='/admin/bank/view' element={<ViewAllBanks/>}/>
        <Route path='/admin/bank/registerForm' element={<AdminRegisterForm/>}/>
        <Route path='/admin/bank/managers' element={<ViewAllBankManagers/>}/>
        <Route path='/admin/bank/customers' element={<ViewAllBankCustomers/>}/>
        <Route path='/admin/add' element={<AddBankAccount/>}/>
        <Route path='/admin/view' element={<ViewBankAccounStatement/>}/>
        <Route path='/admin/customer/view' element={<ViewCustomerTransaction/>}/>
        <Route path='/admin/retrieve' element={<RetrieveAllBankAccounts/>}/>
        <Route path='/customer/transfer' element={<CustomerAccountFundTransfer/>}/>
        <Route path='/OnlineBanking' element={<OnlineBanking/>}/>
        <Route path='/Header' element={<Header/>}/>
        
        </Routes>
        </>       
  )
}

export default App;