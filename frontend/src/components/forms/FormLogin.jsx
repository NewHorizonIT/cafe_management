import React from "react";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { NavLink } from "react-router-dom";
import { ToggleTheme } from "../ui";

const schemaLogin = z.object({
  email: z.string().email("Email Khong hop le"),
  password: z.string().min(2, "Password phai tren 8 ki tu"),
});

const FormLogin = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({
    resolver: zodResolver(schemaLogin),
  });

  const handleLogin = (data) => {
    console.log(data);
  };
  return (
    <form
      onSubmit={handleSubmit(handleLogin)}
      className="bg-base-200 rounded-lg p-8 shadow-md min-w-md w-full mx-auto"
    >
      <h2 className="text-4xl font-bold text-center mb-6 text-base-content">
        Welcome to MyCoffee
      </h2>
      <div className="space-y-4">
        <div>
          <label
            htmlFor="email"
            className="block text-sm font-medium text-base-content"
          >
            Email
          </label>
          <input
            type="email"
            id="email"
            {...register("email")}
            className="mt-1 h-[40px] p-3 border border-base-content text-base-content block w-full rounded-md shadow-sm focus:ring-primary focus:border-primary sm:text-sm"
          />
          {errors.email && (
            <p className="text-red-500 text-xs mt-1">{errors.email.message}</p>
          )}
        </div>
        <div>
          <label
            htmlFor="password"
            className="block text-sm font-medium text-base-content"
          >
            Mật khẩu
          </label>
          <input
            type="password"
            id="password"
            {...register("password")}
            className="mt-1 h-[40px] p-3 border block w-full border-base-content text-base-content rounded-md shadow-sm focus:ring-primary focus:border-primary sm:text-sm"
          />
          {errors.password && (
            <p className="text-red-500 text-xs mt-1">
              {errors.password.message}
            </p>
          )}
        </div>
      </div>
      <div className="mt-6">
        <button
          type="submit"
          className="w-full bg-primary text-white py-2 px-4 rounded-md shadow hover:bg-primary-dark focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary"
        >
          Đăng nhập
        </button>
      </div>
      <p className="mt-4 text-center text-sm text-gray-600">
        Bạn chưa có tài khoản?{" "}
        <NavLink to="/auth/register" className="text-primary hover:underline">
          Đăng ký
        </NavLink>
        {/* <ToggleTheme /> */}
      </p>
    </form>
  );
};

export default FormLogin;
