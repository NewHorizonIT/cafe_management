import { Button } from "@/components/ui";
import Modal from "@/components/ui/Modal";
import React from "react";

const OrderModal = ({ order, isOpen, onClose }) => {
  const handleCancel = (order) => {
    order.status = "Đã huỷ";
  };
  const handleSubmit = (order) => {
    order.status = "Đã giao";
  };
  return (
    <Modal isOpen={isOpen} onClose={onClose} title={"Chi tiet hoa don"}>
      <div>
        <span>ID</span>
        <p>{order?.id}</p>
      </div>
      <div>
        <span>User</span>
        <p>{order?.customer}</p>
      </div>
      <div>
        <span>Date</span>
        <p>{order?.date}</p>
      </div>
      <div>
        <span>Status</span>
        <p>{order?.status}</p>
      </div>
      <div>
        <Button
          className="text-primary-content bg-primary rounded px-3 py-2 min-w-[120px]"
          lable="Xac nhan"
          eventHandler={handleSubmit}
        />
        <Button
          className="text-primary-content bg-primary rounded px-3 py-2 min-w-[120px]"
          lable="Huy"
          eventHandler={handleCancel}
        />
      </div>
    </Modal>
  );
};

export default OrderModal;
