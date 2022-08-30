import {BrowserRouter, Route, Router, Routes} from "react-router-dom";
import AddMovie from "./view/AddMovie";
import NavigationBar from "./component/NavigationBar";
import App from "./App";
import {Box} from "@mui/material";
import Footer from "./component/Footer";
import Home from "./view/Home";

function AppRouter() {



    return (
            <BrowserRouter>
                <div>
                    <NavigationBar/>
                    <Routes>
                        <Route path ="/movie/register" element={<AddMovie/>}></Route>
                        <Route path ="/" element={<Home/>}></Route>
                    </Routes>
                </div>
                <Box mt={5}>
                    <Footer/>
                </Box>
            </BrowserRouter>
    )
}

export default AppRouter