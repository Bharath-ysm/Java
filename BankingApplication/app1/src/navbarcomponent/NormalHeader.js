import { Link } from "react-router-dom";

const NormalHeader = () => 
{
  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <div className="container">
        <ul className="navbar-nav ms-auto mb-2 mb-lg-0 me-5">
          <li className="nav-item">
            <Link to="/registration" className="nav-link active" aria-current="page">
              <b className="text-color">Register Admin ID </b>
            </Link>
          </li>
          <li className="nav-item">
            <Link to="/login" className="nav-link active" aria-current="page">
              <b className="text-color">Login </b>
            </Link>
          </li>
          <li className="nav-item">
            <Link to="/user" className="nav-link active" aria-current="page">
              <b className="text-color">UserRegisterFIelds </b>
            </Link>
          </li>
        </ul>
      </div>
    </nav>
  );
};
export default NormalHeader;



