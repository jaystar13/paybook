/*
import { BrowserRouter, Route, Routes } from "react-router-dom";

import { AuthProvider, RequireAuth } from "./hooks/auth";
import AppLayout from "./components/AppLayout";
import HomeLayout from "./components/HomeLayout";
import HomePage from "./pages/Home";
import Profile from "./pages/Profile";
import Login from "./pages/Login";
import Signup from "./pages/Signup";

export default function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route element={<HomeLayout />}>
            <Route path="/" element={<HomePage />} />
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<Signup />} />
          </Route>
          <Route path="/" element={<AppLayout />}>
            <Route path="profile" element={<Profile />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}
*/
