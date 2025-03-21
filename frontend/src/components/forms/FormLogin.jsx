import React from "react";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { NavLink } from "react-router-dom";

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
      className="bg-base-300 rounded-2xl p-5 shadow-lg min-w-[300px] "
    >
      <h2 className="text-3xl text-center pb-5">Welcome MyCoffe</h2>
      <div className="flex flex-col gap-5">
        <div className="flex flex-col gap-3">
          <label htmlFor="email">Email</label>
          <input
            type="email"
            id="email"
            {...register("email")}
            className="border-2 rounded-sm gap-3"
          />
          {errors.email && (
            <p style={{ color: "red" }}>{errors.email.message}</p>
          )}
        </div>
        <div className="flex flex-col gap-3">
          <label htmlFor="password">Password</label>
          <input
            type="text"
            id="password"
            {...register("password")}
            className="border-2 rounded-sm"
          />
          {errors.password && (
            <p style={{ color: "red" }}>{errors.password.message}</p>
          )}
        </div>
      </div>
      <div className="flex justify-center items-center cursor-pointer">
        <button
          type="submit"
          className="btn-md bg-primary text-primary-content py-2 px-5 text-base rounded-sm mt-5 cursor-pointer"
        >
          Login
        </button>
      </div>
      <p className="text-center text-sm">
        Ban chua co tai khoang?
        <NavLink to="/auth/register" className="text-primary">
          Dang ki
        </NavLink>
      </p>
    </form>
  );
};

export default FormLogin;
