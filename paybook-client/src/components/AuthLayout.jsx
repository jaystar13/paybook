import { LoadingOutlined } from "@ant-design/icons";
import { Alert, Spin } from "antd";
import { Suspense } from "react";
import { Await, useLoaderData, useOutlet } from "react-router-dom";
import { AuthProvider } from "../hooks/useAuth";

export const AuthLayout = () => {
  const loadingIcon = (
    <LoadingOutlined
      style={{
        fontSize: 24,
      }}
      spin
    />
  );

  const outlet = useOutlet();

  const { userPromise } = useLoaderData();

  return (
    <Suspense fallback={<Spin indicator={loadingIcon} />}>
      <Await
        resolve={userPromise}
        errorElement={
          <Alert type="error" message="Something went wrong!" closable />
        }
        children={(user) => (
          <AuthProvider userData={user}>{outlet}</AuthProvider>
        )}
      />
    </Suspense>
  );
};
