import { Modal } from "@/components/ui";
import React from "react";

const UserHistory = () => {
  const orders = [
    {
      id: 1,
      date: "19/04/2025",
      description: "Mua hàng",
      amount: "500,000 VND",
      status: "Đã giao hàng",
      details: [
        { product: "Sản phẩm A", quantity: 2, price: "200,000 VND" },
        { product: "Sản phẩm B", quantity: 1, price: "100,000 VND" },
      ],
    },
    {
      id: 2,
      date: "18/04/2025",
      description: "Mua hàng",
      amount: "1,000,000 VND",
      status: "Đang xử lý",
      details: [{ product: "Sản phẩm C", quantity: 5, price: "1,000,000 VND" }],
    },
  ];

  const [selectedOrder, setSelectedOrder] = React.useState(null);

  return (
    <div className="p-4 md:p-8 bg-transparent">
      <div className="max-w-4xl mx-auto">
        <h1 className="text-2xl md:text-4xl font-bold mb-4">
          Lịch sử giao dịch
        </h1>
        <div className="shadow-md rounded-lg p-4">
          <table className="table-auto w-full">
            <thead>
              <tr className="bg-gray-200">
                <th className="px-4 py-2">Ngày</th>
                <th className="px-4 py-2">Mô tả</th>
                <th className="px-4 py-2">Số tiền</th>
                <th className="px-4 py-2">Tình trạng</th>
                <th className="px-4 py-2">Chi tiết</th>
              </tr>
            </thead>
            <tbody>
              {orders.map((order) => (
                <tr key={order.id}>
                  <td className="border px-4 py-2">{order.date}</td>
                  <td className="border px-4 py-2">{order.description}</td>
                  <td className="border px-4 py-2">{order.amount}</td>
                  <td className="border px-4 py-2">{order.status}</td>
                  <td className="border px-4 py-2">
                    <button
                      className="btn btn-sm btn-primary"
                      onClick={() => setSelectedOrder(order)}
                    >
                      Xem
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
        {selectedOrder && (
          <Modal
            isOpen={!!selectedOrder}
            onClose={() => setSelectedOrder(null)}
          >
            <div className="mt-4 bg-transparent rounded-lg p-4">
              <h2 className="text-xl font-bold mb-2">Chi tiết đơn hàng</h2>
              <p className="mb-2">Ngày: {selectedOrder.date}</p>
              <p className="mb-2">Tình trạng: {selectedOrder.status}</p>
              <h3 className="text-lg font-semibold">Sản phẩm:</h3>
              <ul className="list-disc list-inside">
                {selectedOrder.details.map((item, index) => (
                  <li key={index}>
                    {item.product} - Số lượng: {item.quantity} - Giá:{" "}
                    {item.price}
                  </li>
                ))}
              </ul>
              <button
                className="btn btn-sm bg-primary text-primary-content mt-4"
                onClick={() => setSelectedOrder(null)}
              >
                Đóng
              </button>
            </div>
          </Modal>
        )}
      </div>
    </div>
  );
};

export default UserHistory;
