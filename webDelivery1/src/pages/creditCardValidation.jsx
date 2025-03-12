import React from "react";


function MiH2() {
    const[text, setText] = React.useState("");
    const [valid, setValid] = React.useState("");

    const hanldeChange = (e) => {
        setText(e.target.value);

        if (text.length == 16) {
            let odd = 0;
            let even = 0;
            let double = 0;
            let res = 0;
            
            for (let i = text.length-1; i > -1; i-=2) {
                
                odd += parseInt(text[i]);



                double = text[i-1] * 2;
                if (double > 9) {
                    let sum = 0;
                    let str = double.toString();
                    sum = parseInt(str[0]) + parseInt(str[1]);
                    double = sum;
                }

                
                even = even + double;
            }
            
            res = even + odd;
            if (res % 10 == 0) {
                // console.log('valid credit card');
                setValid('valid credit card');
            }else{
                // console.log('invalid credit card');
                setValid('invalid credit card');
            }
            
        }
        

    }

    return (
        <>
        <div className="h-2/6 w-2/6  bg-[#F4EDD3] rounded-lg shadow-[0_3px_10px_rgb(0,0,0,0.2)] flex justify-center items-center flex-col ">
          <div className="flex flex-col justify-center items-center">
            <input type="text"  className='border-1 rounded-md' onChange={(e) => setText(e.target.value)} />
            <p>{valid}</p>
          </div>
          <div className="flex justify-center items-start py-4">
            <button className="px border-1 bg-[#626F47] px-1 text-white w-25 h-10 rounded-lg" onClick={hanldeChange}>Check</button>
          </div>
        </div>
        </>
    );
}

export default MiH2;