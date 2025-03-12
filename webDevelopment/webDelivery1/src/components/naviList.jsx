import React from "react";
import { NavLink } from "react-router-dom";

function NaviList() {
    return (
        <div className='bg-[#579BB1] rounded-b-2lg shadow-[0_3px_10px_rgb(0,0,0,0.2)]'> 

            <nav className="flex flex-row justify-center space-x-4 p-4 ">
                <div className="hover:bg-[#4C585B] rounded-lg h-10 w-20 flex justify-center items-center">
                    <NavLink to="/" className='text-white'>Home</NavLink>
                </div>

                <div className="hover:bg-[#4C585B] rounded-lg h-10 w-20 flex justify-center items-center">

                    <NavLink to="/WordList" className='text-white'>Word List</NavLink>
                </div>

                <div className="hover:bg-[#4C585B] rounded-lg h-10 w-20 flex justify-center items-center">

                    <NavLink to="/albumList" className='text-white'>API fetch</NavLink>
                </div >

                <div className="hover:bg-[#4C585B] rounded-lg h-10 w-auto flex justify-center items-center">
                <NavLink to="/miH2" className='text-white'>Credit card validator</NavLink>
                </div>
            </nav>
        </div>

    );
}

export default NaviList;
