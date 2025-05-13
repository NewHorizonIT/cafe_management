import React from "react";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { NavLink, useNavigate } from "react-router-dom";
import api from "@/api/api";
import useAuthStore from "@/store/useAuthStore";

const schemaRegister = z.object({
  username: z.string().min(4, "Ten toi thieu phai 4 ki tu"),
  email: z.string().email("Email Khong hop le"),
  password: z.string().min(2, "Password phai tren 8 ki tu"),
  phone: z.string().min(10, "So dien thoai phai co 10 so"),
  address: z.string().min(5, "Dia chi phai co it nhat 5 ki"),
});
const FormRegister = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({
    resolver: zodResolver(schemaRegister),
  });

  const { setData, setIsLogin } = useAuthStore();
  const navigate = useNavigate();

  const handleRegister = async (data) => {
    data = {
      ...data,
      roleId: 1,
      status: true,
    };
    try {
      const res = await api.post("/users/register", data);
      if (res.status === 200) {
        alert("Đăng ký thành công");
      }
      setData(res.data);
      setIsLogin(true);
      navigate("/");
    } catch (error) {
      console.error("Error during registration:", error);
      alert("Đăng ký thất bại");
    }
  };
  return (
    <form
      onSubmit={handleSubmit(handleRegister)}
      className="bg-base-200 rounded-lg p-8 shadow-md w-full min-w-md mx-auto"
    >
      <h2 className="text-4xl font-bold text-center mb-6 text-base-content">
        Tham gia MyCoffee
      </h2>
      <div className="space-y-4">
        <div>
          <label
            htmlFor="username"
            className="block text-sm font-medium text-base-content"
          >
            Username
          </label>
          <input
            type="text"
            id="username"
            {...register("username")}
            className="mt-1 h-[40px] block w-full border border-base-content rounded-md shadow-sm focus:ring-primary focus:border-primary sm:text-sm"
          />
          {errors.username && (
            <p className="text-red-500 text-xs mt-1">
              {errors.username.message}
            </p>
          )}
        </div>
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
            className="mt-1 h-[40px] block w-full border border-base-content rounded-md shadow-sm focus:ring-primary focus:border-primary sm:text-sm"
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
            Mât khẩu
          </label>
          <input
            type="password"
            id="password"
            {...register("password")}
            className="mt-1 h-[40px] block w-full border border-base-content rounded-md shadow-sm focus:ring-primary focus:border-primary sm:text-sm"
          />
          {errors.password && (
            <p className="text-red-500 text-xs mt-1">
              {errors.password.message}
            </p>
          )}
        </div>
        <div>
          <label
            htmlFor="address"
            className="block text-sm font-medium text-base-content"
          >
            Địa chỉ
          </label>
          <input
            type="text"
            id="address"
            {...register("address")}
            className="mt-1 h-[40px] block w-full border border-base-content rounded-md shadow-sm focus:ring-primary focus:border-primary sm:text-sm"
          />
          {errors.address && (
            <p className="text-red-500 text-xs mt-1">
              {errors.address.message}
            </p>
          )}
        </div>
        <div>
          <label
            htmlFor="phon"
            className="block text-sm font-medium text-base-content"
          >
            Số điện thoại
          </label>
          <input
            type="text"
            id="phone"
            {...register("phone")}
            className="mt-1 h-[40px] block w-full border border-base-content rounded-md shadow-sm focus:ring-primary focus:border-primary sm:text-sm"
          />
          {errors.phone && (
            <p className="text-red-500 text-xs mt-1">{errors.phone.message}</p>
          )}
        </div>
      </div>
      <div className="mt-6">
        <button
          type="submit"
          className="w-full bg-primary text-white py-2 px-4 rounded-md shadow hover:bg-primary-dark focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary"
        >
          Đăng ký
        </button>
      </div>
      <p className="mt-4 text-center text-sm text-gray-600">
        Bạn đã có tài khoản?{" "}
        <NavLink to="/auth/login" className="text-primary hover:underline">
          Đăng nhập
        </NavLink>
      </p>
      {/* <ToggleTheme /> */}
    </form>
  );
};

export default FormRegister;
