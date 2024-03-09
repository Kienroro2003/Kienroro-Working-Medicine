import { Link } from "react-router-dom";
import { useAuth } from "../contexts/authProvider";

export function Navbar() {
  const [auth, setAuth] = useAuth();
  return (
    <nav className="navbar navbar-expand-lg bg-primary text-light flex justify-between pr-3">
      <div>
        <Link
          className="navbar-brand"

          style={{ color: "white", marginLeft: "10px" }}
         to={'#'}>
          Home
        </Link>
        <div style={{ display: "inline-block" }}>
          <ul className="navbar-nav">
            <li className="nav-item dropdown">
              <Link
                className="nav-link dropdown-toggle"
                id="navbarDropdownMenuLink"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                style={{ color: "white" }}
               to={'#'}>
                Quản lý bán hàng
              </Link>
              <ul
                className="dropdown-menu"
                aria-labelledby="navbarDropdownMenuLink"
              >
                <li>
                  <Link className="dropdown-item" to="/bill/list">
                    Hóa đơn bán hàng
                  </Link>
                </li>
              </ul>
            </li>
          </ul>
        </div>
        <div style={{ display: "inline-block", marginLeft: "10px" }}>
          <ul className="navbar-nav">
            <li className="nav-item dropdown">
              <Link
                className="nav-link dropdown-toggle"
                to="#"
                id="navbarDropdownMenuLink"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                style={{ color: "white" }}
              >
                Quản lý kho bãi
              </Link>
              <ul
                className="dropdown-menu"
                aria-labelledby="navbarDropdownMenuLink"
              >
                <li>
                  <Link className="dropdown-item" to="/inputWarehouse/list">
                    Quản lý nhập kho
                  </Link>
                </li>
                <li>
                  <Link className="dropdown-item" to="/medicine/list">
                    Danh sách thuốc
                  </Link>
                </li>
                <li>
                  <Link className="dropdown-item" to="/typeMedicine/list">
                    Quản lý loại thuốc
                  </Link>
                </li>
              </ul>
            </li>
          </ul>
        </div>
        <div style={{ display: "inline-block", marginLeft: "10px" }}>
          <ul className="navbar-nav">
            <li className="nav-item dropdown">
              <Link
                className="nav-link dropdown-toggle"
                to="#"
                id="navbarDropdownMenuLink"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                style={{ color: "white" }}
              >
                Quản lý thông tin
              </Link>
              <ul
                className="dropdown-menu"
                aria-labelledby="navbarDropdownMenuLink"
              >
                <li>
                  <Link className="dropdown-item" to="/member/list">
                    Khách hàng
                  </Link>
                </li>
                <li>
                  <Link className="dropdown-item" to="/supplier/list">
                    Nhà cung cấp
                  </Link>
                </li>
                <li>
                  <Link className="dropdown-item" to="/employee/list">
                    Nhân viên
                  </Link>
                </li>
              </ul>
            </li>
          </ul>
        </div>
        <div style={{ display: "inline-block", marginLeft: "10px" }}>
          <ul className="navbar-nav">
            <li className="nav-item dropdown">
              <Link
                className="nav-link dropdown-toggle"
                to="#"
                id="navbarDropdownMenuLink"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                style={{ color: "white" }}
              >
                Quản lý doanh thu
              </Link>
              <ul
                className="dropdown-menu"
                aria-labelledby="navbarDropdownMenuLink"
              >
                <li>
                  <Link className="dropdown-item" to="#">
                    Theo Ngày
                  </Link>
                </li>
                <li>
                  <Link className="dropdown-item" to="#">
                    Tháng/Năm
                  </Link>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
      {!auth ? (
        <>
          <div className="flex gap-5">
            <Link to="/login" className="px-4 py-2 rounded-md bg-blue-500">
              Login
            </Link>
            <Link to="/register" className="px-4 py-2 rounded-md bg-blue-500">
              Register
            </Link>
          </div>
        </>
      ) : (
        <>{auth?.username}</>
      )}
    </nav>
  );
}
