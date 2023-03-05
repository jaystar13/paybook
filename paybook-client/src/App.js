import { BrowserRouter, Route, Routes } from "react-router-dom";

import { AuthProvider, RequireAuth } from "./context/Auth";
import AppLayout from "./components/AppLayout";
import HomePage from "./pages/Home";
import Login from "./pages/user/login/Login";
import Signup from "./pages/user/signup/Signup";

export default function Apptwo() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route element={<AppLayout />}>
            <Route path="/" element={<HomePage />} />
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<Signup />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}
