import React, { useEffect, useRef } from "react";

const Modal = ({ isOpen, title, children, onClose }) => {
  const modalRef = useRef(null);

  useEffect(() => {
    if (isOpen) {
      modalRef.current?.showModal();
    } else {
      modalRef.current?.close();
    }
  }, [isOpen]);

  return (
    <dialog id="modal" className="modal" ref={modalRef} onClose={onClose}>
      <div className="modal-box">
        <form method="dialog">
          <button
            className="btn btn-sm btn-circle btn-ghost absolute right-2 top-2"
            onClick={onClose}
          >
            ✕
          </button>
        </form>
        {title && <h3 className="text-lg font-bold">{title}</h3>}
        <div className="py-4">{children}</div>
      </div>
    </dialog>
  );
};

export default Modal;
