import { NavLink } from "react-router-dom";
import React from 'react'

export default function Header(props){
    return(
        <header>
            <div className="d-flex flex-row headerContainer">
                <div className="d-flex flex-row ml-3 ms-3 mt-auto mb-auto align-items-center">
                    <a>
                        <img src="logo.png" alt="*" width="60" height="60" className="align-text-top"></img>
                    </a>
                    <div id="logoName">
                        <a href="mainPage">boxStore</a>
                    </div>
                </div>
                <nav className="navbar navbar-expand-md">
                    <div className="container-fluid" id="navigationMenu">
                        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                            <span className="navbar-toggler-icon"></span>
                        </button>
                        <div className="navbar-collapse collapse justify-content-end" id="navbarNav">
                            <ul className="navbar-nav" id="headerNavigation">
                            {
                                props.links.map(route =>
                                    <li key={route.path}
                                        className="nav-item">
                                        <NavLink className="nav-link navigationCaption" to={route.path}>
                                            {route.label}
                                        </NavLink>
                                    </li>
                                )
                            }
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>
        </header>
    );
}