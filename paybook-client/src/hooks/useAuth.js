import { createContext, useContext, useMemo } from "react";
import { useNavigate } from "react-router-dom";

import { notification } from "antd";
import { login as loginApi } from "./api";
import { useLocalStorage } from "./useLocalStorage";
import { ACCESS_TOKEN } from "../constants";

const AuthContext = createContext();

export const AuthProvider = ({ children, userData }) => {
  const [user, setUser] = useLocalStorage("user", userData);
  const navigate = useNavigate();

  const login = async (data) => {
    await loginApi(data)
      .then((res) => {
        localStorage.setItem(ACCESS_TOKEN, res.accessToken);

        setUser({ data });
        navigate("/profile", { replace: true });

        return {
          status: 200,
        };
      })
      .catch((err) => {
        return {
          status: err.response.status,
        };
      });
  };

  const logout = () => {
    setUser(null);
    navigate("/", { replace: true });
  };

  const value = useMemo(
    () => ({
      user,
      login,
      logout,
    }),
    [user]
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};

export const useAuth = () => {
  return useContext(AuthContext);
};
