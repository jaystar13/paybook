import {
  createBrowserRouter,
  createRoutesFromElements,
  defer,
  Route,
} from "react-router-dom";
import { AppLayout } from "./components/AppLayout";
import { AuthLayout } from "./components/AuthLayout";
import { HomeLayout } from "./components/HomeLayout";
import { Home } from "./pages/Home";
import { Login } from "./pages/Login";
import { Profile } from "./pages/Profile";
import { Signup } from "./pages/Signup";

const getUserData = () =>
  new Promise((resolve) =>
    setTimeout(() => {
      const user = window.localStorage.getItem("user");
      resolve(user);
    }, 3000)
  );

export const router = createBrowserRouter(
  createRoutesFromElements(
    <Route
      element={<AuthLayout />}
      loader={() => defer({ userPromise: getUserData() })}
    >
      <Route element={<HomeLayout />}>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
      </Route>

      <Route path="/paybook" element={<AppLayout />}>
        <Route path="profile" element={<Profile />} />
      </Route>
    </Route>
  )
);
