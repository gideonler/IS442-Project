<template>
    <!-- Vertical navbar -->
    <div class="vertical-nav bg-white fixed-right" id="sidebar">
        <div class="py-4 px-3 mb-4">
            <div class="media d-flex align-items-center"><img
                    src="https://therichpost.com/wp-content/uploads/2020/06/avatar2.png" alt="..." width="65"
                    class="mr-3 rounded-circle img-thumbnail shadow-sm">
                <div class="media-body personal">
                    <h4 class="m-0">Mr Sporty</h4>
                    <p class="font-weight-light text-muted mb-0">Sportsman of the Year</p>
                    <p class="font-weight-light text-muted mb-0">mrsporty@sss.edu.sg</p>
                </div>
            </div>
        </div>

        <p class="bg-light text-gray font-weight-bold text-uppercase px-3 py-3 mb-0">Bookings</p>

        <ul class="nav flex-column bg-white mb-0">
            <li class="nav-item">
                <a href="#" class="nav-link text-dark font-italic">
                    <i class="fa fa-address-card mr-3 text-primary fa-fw"></i>
                    You have 2 bookings left for October
                </a>
            </li>
        </ul>

        <p class="bg-light text-gray font-weight-bold text-uppercase px-3 py-3 mt-2 mb-0">Current Bookings</p>

        <div data-bs-spy="scroll" data-bs-target="#navbar-example2" data-bs-offset="0" class="scrollspy-example scrollspy" tabindex="0"> 
            <ul class="nav flex-column bg-white mb-0">
                <li class="nav-item">
                    <div class="ms-2 me-auto">
                        <div class="fw-bold">Booking 1</div>
                        <ul>
                            <li>Destination: Singapore Zoo</li>
                            <li>Date: 1st October 2022</li>
                            <li>Number of tickets: 2</li>
                        </ul>                
                    </div>
                    <button class="btn btn-secondary btn-sm mt-1 ml-5">Cancel Booking</button>   
                    <!-- move to styling              -->
                </li>
                <li class="nav-item">
                    <div class="mt-3 ms-2 me-auto">
                        <div class="fw-bold">Booking 2</div>
                        <ul>
                            <li>Destination: Gardens By The Bay</li>
                            <li>Date: 31st October 2022</li>
                            <li>Number of tickets: 2</li>
                        </ul>                
                    </div>
                    <button class="btn btn-secondary btn-sm mt-1 ml-5">Cancel Booking</button>                
                </li>
            </ul>
        </div>

        <p class="bg-light text-gray font-weight-bold text-uppercase px-3 py-3 mt-2 mb-0">Note</p>

        <ul class="nav flex-column bg-white mb-0">
            <li class="nav-item">
                <div class="ms-2 me-auto">
                    <div class="fw-bold">Information</div>
                    <ul>
                        <li>Passes can be collected 1 day before the trip from the General Office</li>
                        <li>Passes must be returned to the General Office the day after the trip</li>
                    </ul>                
                </div>
            </li>
            <li class="nav-item">
                <div class="mt-3 ms-2 me-auto">
                    <div class="fw-bold">Contact</div>
                    <ul>
                        <li>General Office Phone: 12345678</li>
                    </ul>
                    <ul>
                        <li> Report loss and found <a href="#"> here </a></li>
                    </ul>
                    <ul>
                        <li>For futher queries, please contact 
                            <p class="text-primary text-decoration-underline">ineedhelp@sss.edu.sg</p>
                        </li>
                    </ul>                
                </div>         
            </li>
        </ul>

    </div>
    <!-- End vertical navbar -->
</template>
  
<script>
export default {
    name: 'SideBar',
    props: {
        msg: String
    }
}
</script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.vertical-nav {
    min-width: 10rem;
    width: 25rem;
    height: 100vh;
    position: fixed;
    top: 0;
    right: 0;
    box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.1);
    transition: all 0.4s;
}

/* for toggle behavior */
#sidebar.active {
    margin-left: -17rem;
}

#content.active {
    width: 100%;
    margin: 0;
}

@media (max-width: 768px) {
    #sidebar {
        margin-left: -17rem;
    }

    #sidebar.active {
        margin-left: 0;
    }

    #content {
        width: 100%;
        margin: 0;
    }

    #content.active {
        margin-left: 17rem;
        width: calc(100% - 17rem);
    }
}

.personal {
    transform: translate(10px, 10px);
}

.scrollspy {
    position: relative;
}

body {
    background: #599fd9;
    background: -webkit-linear-gradient(to right, #599fd9, #c2e59c);
    background: linear-gradient(to right, #599fd9, #c2e59c);
    min-height: 100vh;
    overflow-x: hidden;
}

.separator {
    margin: 3rem 0;
    border-bottom: 1px dashed #fff;
}

.text-uppercase {
    letter-spacing: 0.1em;
}

.text-gray {
    color: #aaa;
}
</style>