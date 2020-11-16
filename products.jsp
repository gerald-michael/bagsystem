<%@ include file="header.jsp" %>
    <%-- <h1>Products</h1>
    <form action="create" method="post">
      <label for="name">Name</label><input type="text" name="name" id="name">
      <label for="description">Description</label><input type="text" name="description" id="description">
      <input type="submit" value="create">
    </form> --%>


    <div class="row">
        <%@ include file="sidebar.jsp"%>

        <div class="col s9">
            <!-- intro stock -->
            

            <!-- form begin -->
            <div class="row">
                <h5>Stock entry</h5>
                <form action="create" method="post" >
                    <div class="row">
                        <div class="col s2">Brand</div>
                        <div class="col s2">Color</div>
                        <div class="col s2">Size</div>
                        <div class="col s2">Type</div>
                        <div class="col s2">Cost</div>
                        <div class="col s2">Quantity</div>
                    </div>
                    <div class="row" id="items">
                        <div id="stock" class="row">
                            <!-- Brand -->
                            <div class="input-field col s2">
                                <select class="browser-default">
                                <option value="" disabled selected>Choose Brand</option>
                                <option value="1">Option 1</option>
                                <option value="2">Option 2</option>
                                <option value="3">Option 3</option>
                                </select>
                               
                            </div>
                            <!-- Color -->
                            <div class="input-field col s2">
                                <select class="browser-default">
                                <option value="" disabled selected>Choose color</option>
                                <option value="1">Option 1</option>
                                <option value="2">Option 2</option>
                                <option value="3">Option 3</option>
                                </select>
                             
                            </div>
                            <!-- Size -->
                            <div class="input-field col s2">
                                <select class="browser-default">
                                <option value="" disabled selected>Choose Size</option>
                                <option value="1">Option 1</option>
                                <option value="2">Option 2</option>
                                <option value="3">Option 3</option>
                                </select>
                                
                            </div>
                            
                            <!-- Type -->
                            <div class="input-field col s2">
                                <select  class="browser-default">
                                <option value="" selected>Choose Type</option>
                                <option value="1">Option 1</option>
                                <option value="2">Option 2</option>
                                <option value="3">Option 3</option>
                                </select>
                                
                            </div>
                            <!-- Cost -->
                            <div class="input-field col s2">
                                <input type="number" min="1">
                                <label>Cost</label>
                            </div>
                            <!-- Quantity -->
                            <div class="input-field col s2">
                                <input type="number" min="1">
                                <label>Bags</label>
                            </div>
                            
                        </div>
                    </div>

                    <div class="row">
                        <div class="col s4">
                            <a id="add_stock_item" class="btn-floating btn-medium waves-effect waves-light red" onclick="bing()"><i class="material-icons">add</i></a>
                            <!-- <span class="card  large s4">Add stock</span> -->
                        </div>
                      
                        <div class="col s4">
                            <button class="btn waves-effect waves-light" type="submit" name="action">Submit
                                <i class="material-icons right">upload</i>
                              </button>
                        </div>
                    </div>
                   
                </form>

            </div>
            <!-- form end -->

            <!-- New Stock Summary -->
            <div class="row">
                <div class="col s12 m6">
                    <div class="card">
                      <div class="card-title">
                          Just added Stock
                        <!-- <a class="btn-floating halfway-fab waves-effect waves-light red"><i class="material-icons">add</i></a> -->
                      </div>
                      <div class="card-content">
                        <p>View details</p>
                      </div>
                    </div>
                </div>
            </div>


          <%-- Create product --%>
              <div class=" row">
                <h1>Products</h1>
                <form action="create" method="post">
                  <label for="name">Name</label><input type="text" name="name" id="name">
                  <label for="description">Description</label><input type="text" name="description" id="description">
                  <input type="submit" value="create">
                </form>
              </div>


            <!-- Current stock -->
            <table class="centered highlight responsive-table striped">
                <thead>
                  <tr>
                      <th>Brand</th>
                      <th>Type</th>
                      <th>Color</th>
                      <th>Size</th>
                      <th>Quantity</th>
                  </tr>
                </thead>
        
                <tbody>
                  <tr>
                    <td>Ponasoo</td>
                    <td>Computer Bag</td>
                    <td>Blue</td>
                    <td>Small</td>
                    <td>40</td>
                  </tr>
                  <tr>
                    <td>Ponasoo</td>
                    <td>Computer Bag</td>
                    <td>Blue</td>
                    <td>Medium</td>
                    <td>30</td>
                  </tr>
                  <tr>
                    <td>Ponasoo</td>
                    <td>Computer Bag</td>
                    <td>Red</td>
                    <td>Small</td>
                    <td>23</td>
                  </tr>
                  <tr>
                    <td>Ponasoo</td>
                    <td>Computer Bag</td>
                    <td>Red</td>
                    <td>Medium</td>
                    <td>3</td>
                  </tr>
                  <tr>
                    <td>Ponasoo</td>
                    <td>Computer Bag</td>
                    <td>Grey</td>
                    <td>Small</td>
                    <td>2</td>
                  </tr>
                  <tr>
                    <td>Ponasoo</td>
                    <td>Computer Bag</td>
                    <td>Grey</td>
                    <td>Medium</td>
                    <td>32</td>
                  </tr>

                  <tr>
                    <td>Ponasoo</td>
                    <td>Hand Bag</td>
                    <td>Blue</td>
                    <td>Small</td>
                    <td>40</td>
                  </tr>
                  <tr>
                    <td>Ponasoo</td>
                    <td>Hand Bag</td>
                    <td>Blue</td>
                    <td>Medium</td>
                    <td>30</td>
                  </tr>
                  <tr>
                    <td>Ponasoo</td>
                    <td>Hand Bag</td>
                    <td>Red</td>
                    <td>Small</td>
                    <td>23</td>
                  </tr>
                  <tr>
                    <td>Ponasoo</td>
                    <td>Hand Bag</td>
                    <td>Red</td>
                    <td>Medium</td>
                    <td>3</td>
                  </tr>
                  <tr>
                    <td>Ponasoo</td>
                    <td>Hand Bag</td>
                    <td>Grey</td>
                    <td>Small</td>
                    <td>2</td>
                  </tr>
                  <tr>
                    <td>Ponasoo</td>
                    <td>Hand Bag</td>
                    <td>Grey</td>
                    <td>Medium</td>
                    <td>32</td>
                  </tr>

                  <tr>
                    <td>Ponasoo</td>
                    <td>Back Pack</td>
                    <td>Blue</td>
                    <td>Small</td>
                    <td>40</td>
                  </tr>
                  <tr>
                    <td>Ponasoo</td>
                    <td>Back Pack</td>
                    <td>Blue</td>
                    <td>Medium</td>
                    <td>30</td>
                  </tr>
                  <tr>
                    <td>Ponasoo</td>
                    <td>Back Pack</td>
                    <td>Red</td>
                    <td>Small</td>
                    <td>23</td>
                  </tr>
                  <tr>
                    <td>Ponasoo</td>
                    <td>Back Pack</td>
                    <td>Red</td>
                    <td>Medium</td>
                    <td>3</td>
                  </tr>
                  <tr>
                    <td>Ponasoo</td>
                    <td>Back Pack</td>
                    <td>Grey</td>
                    <td>Small</td>
                    <td>2</td>
                  </tr>
                  <tr>
                    <td>Ponasoo</td>
                    <td>Back Pack</td>
                    <td>Grey</td>
                    <td>Medium</td>
                    <td>32</td>
                  </tr>
                </tbody>
              </table>
        </div>

     
    </div>
    <script type="text/javascript" src="js/materialize.js"></script>
<%@ include file="footer.jsp" %>