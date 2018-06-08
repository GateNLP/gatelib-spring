/*
 *  SoftIdleCommonsPoolTargetSource.java
 *
 *  Copyright (c) 1995-2012, The University of Sheffield. See the file
 *  COPYRIGHT.txt in the software or at http://gate.ac.uk/gate/COPYRIGHT.txt
 *
 *  This file is part of GATE (see http://gate.ac.uk/), and is free
 *  software, licenced under the GNU Library General Public License,
 *  Version 2, June 1991 (in the distribution as file licence.html,
 *  and also available at http://gate.ac.uk/gate/licence.html).
 *
 *  Ian Roberts, 19/Oct/2017
 *
 *  $Id: PoolFiller.java 17530 2014-03-04 15:57:43Z markagreenwood $
 */
package gate.util.spring.xml;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.aop.target.CommonsPool2TargetSource;

/**
 * CommonsPool2TargetSource that sets the softMinEvictableIdleTimeMillis on the
 * underlying pool instead of the "hard" minEvictableIdleTimeMillis. The "soft"
 * one respects minIdle, the "hard" one shuts down all idle instances ignoring
 * minIdle.
 * 
 * @author Ian Roberts
 */
public class SoftIdleCommonsPool2TargetSource extends CommonsPool2TargetSource {

  private static final long serialVersionUID = -6668052870905847355L;

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  protected ObjectPool createObjectPool() {
    GenericObjectPoolConfig config = new GenericObjectPoolConfig();
    config.setMaxTotal(this.getMaxSize());
    config.setMaxIdle(this.getMaxIdle());
    config.setMinIdle(this.getMinIdle());
    config.setMaxWaitMillis(this.getMaxWait());
    config.setTimeBetweenEvictionRunsMillis(this.getTimeBetweenEvictionRunsMillis());
    // Next line is the only difference from superclass - set "soft"
    // rather than "hard" minEvictableIdleTimeMillis
    config.setSoftMinEvictableIdleTimeMillis(this.getMinEvictableIdleTimeMillis());
    config.setBlockWhenExhausted(this.isBlockWhenExhausted());
    return new GenericObjectPool(this, config);
  }
  
}
