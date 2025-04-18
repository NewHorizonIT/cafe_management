import clsx from "clsx";
import React from "react";

const Input = ({
  size,
  icon,
  placeholder,
  type,
  data = "",
  value,
  onChange,
  defaultValue,
  readOnly,
  ...props
}) => {
  if (value !== undefined && onChange === undefined && !readOnly) {
    console.warn(
      "You provided a `value` prop to a form field without an `onChange` handler. This will render a read-only field. If the field should be mutable, use `defaultValue`. Otherwise, set either `onChange` or `readOnly`."
    );
  }

  return (
    <label className="input">
      {icon ? icon : <></>}
      <input
        type={type}
        required
        value={value}
        onChange={onChange}
        defaultValue={defaultValue}
        readOnly={readOnly}
        placeholder={placeholder}
        className={clsx("input focus:outline-none!", {
          "input-xs": size === "xs",
          "input-md": size === "md",
          "input-lg": size === "lg",
          "input-xl": size === "xl",
        })}
        {...props}
      />
    </label>
  );
};

export default Input;
