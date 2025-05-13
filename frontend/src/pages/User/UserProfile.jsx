import useAuthStore from "@/store/useAuthStore";
import React from "react";

const UserProfile = () => {
  const { data } = useAuthStore();
  console.log(data);
  return (
    <div className="p-4 md:p-8 bg-base-100">
      <div className="max-w-4xl mx-auto">
        <h1 className="text-2xl md:text-4xl font-bold mb-4">
          Thông tin người dùng
        </h1>
        <div className="bg-base-300 shadow-md rounded-lg p-4">
          <div className="flex flex-col md:flex-row items-center gap-4">
            <img
              src="https://via.placeholder.com/150"
              alt="Avatar"
              className="w-24 h-24 rounded-full border"
            />
            <div>
              <h2 className="text-xl font-semibold">{data.username}</h2>
              <p className="text-gray-600">{data.email}</p>
            </div>
          </div>
          <div className="mt-4">
            <h3 className="text-lg font-semibold">Thông tin chi tiết</h3>
            <ul className="list-disc list-inside">
              <li>Số điện thoại: {data.phone}</li>
              <li>Địa chỉ: {data.address}</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserProfile;
