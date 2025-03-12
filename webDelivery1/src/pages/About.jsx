import React from 'react';
function About() {

    const [show, setShow] = React.useState(true);

    const toggle = () => {
        setShow(!show);
    }
    return (
        <>
        <div className='py-4 flex flex-col justify-center items-center'>
            <button onClick={toggle}  className='hover:bg-[#4C585B]'>Toggle</button>
            {show && <p>This is a hidden text.</p>}
        </div>

        </>
    );
}

export default About;