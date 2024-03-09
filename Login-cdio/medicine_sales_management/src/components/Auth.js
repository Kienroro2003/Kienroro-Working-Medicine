import {useAuth} from "../contexts/authProvider";
import {useNavigate} from "react-router-dom";

export function Auth(){
    const navigate = useNavigate();
    const [auth, setAuth] = useAuth();
    if(!auth)navigate("/login",{ state: { toastMessage: "Vui long dang nhap!" } });
    else console.log("Out")
}